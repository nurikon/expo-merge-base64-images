// Reexport the native module. On web, it will be resolved to ExpoMergeBase64ImagesModule.web.ts
// and on native platforms to ExpoMergeBase64ImagesModule.ts
export { default } from './ExpoMergeBase64ImagesModule';
export { default as ExpoMergeBase64ImagesView } from './ExpoMergeBase64ImagesView';
export * from  './ExpoMergeBase64Images.types';
